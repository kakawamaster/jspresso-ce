/*
 * Copyright (c) 2005-2017 Vincent Vandenschrick. All rights reserved.
 *
 *  This file is part of the Jspresso framework.
 *
 *  Jspresso is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jspresso is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jspresso.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A Widget showing an OpenStreetMap map.
 */
qx.Mixin.define("org.jspresso.framework.view.qx.MMapMixin", {

  construct: function () {
    this.__geolocationEnabled = qx.core.Environment.get("html.geolocation");
  },


  members: {
    __map: null,
    __geolocationEnabled: false,
    __markersLayer: null,
    __markersSource: null,
    __routesLayer: null,
    __routesSource: null,


    _initializeMap: function () {
      if (this.__geolocationEnabled) {
        this.__initGeoLocation();
      }
      this.__createMap();
    },


    /**
     * Calls a redraw of the map. Needed after orientationChange event
     * and drawing markers.
     */
    _redrawMap: function () {
      if (this.__map !== null) {
        this.__map.updateSize();
      }
    },

    __createMap: function () {
      this.__map = new ol.Map({target: this._getMapDomTarget()});

      var osmLayer = new ol.layer.Tile({
        source: new ol.source.OSM()
      });
      this.__map.addLayer(osmLayer);

      var markerStyle = new ol.style.Style({
        image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ {
          anchor: [0.5, 46],
          anchorXUnits: 'fraction',
          anchorYUnits: 'pixels',
          opacity: 0.75,
          src: qx.util.ResourceManager.getInstance().toUri("org/jspresso/framework/map_marker.png")
        })
      });
      var markerFeature = new ol.Feature({
        geometry: new ol.geom.Point(ol.proj.fromLonLat([0, 0]))
      });
      this.__markersSource = new ol.source.Vector({
        features: [markerFeature]
      });
      this.__markersLayer = new ol.layer.Vector({
        source: this.__markersSource,
        style: markerStyle
      });
      this.__map.addLayer(this.__markersLayer);

      var routeStyle = new ol.style.Style({
        stroke: new ol.style.Stroke({
          color: '#3dadff',
          width: 4
        })
      });
      var routeFeature = new ol.Feature({
        geometry: new ol.geom.LineString([[0, 0]])
      });
      this.__routesSource = new ol.source.Vector({
        features: [routeFeature]
      });
      this.__routesLayer = new ol.layer.Vector({
        source: this.__routesSource,
        style: routeStyle
      });
      this.__map.addLayer(this.__routesLayer);
      this.__map.setView(new ol.View({
        center: ol.proj.fromLonLat([0,0]),
        zoom: 12
      }));
    },

    zoomToPosition: function (lonLat, zoom) {
      if (this.__map) {
        var view = this.__map.getView();
        if (lonLat) {
          view.setCenter(ol.proj.fromLonLat(lonLat));
        }
        if (zoom) {
          view.setZoom(zoom);
        }
      }
    },

    drawMapContent: function (markers, routes) {
      if (this.__map) {
        var coordinates = [];
        if (markers && markers.length > 0) {
          var markersFeatures = [];
          for (var i = 0; i < markers.length; i++) {
            var markerNode = ol.proj.fromLonLat(markers[i]);
            markersFeatures.push(new ol.Feature({
              geometry: new ol.geom.Point(markerNode)
            }));
            coordinates.push(markerNode);
          }
          this.__markersSource.clear(true);
          this.__markersSource.addFeatures(markersFeatures);
          this.__markersLayer.setVisible(true);
        } else {
          this.__markersLayer.setVisible(false);
        }
        if (routes && routes.length > 0) {
          var routesFeatures = [];
          for (var i = 0; i < routes.length; i++) {
            var routeNodes = [];
            var route = routes[i];
            for (var j = 0; j < route.length; j++) {
              var routeNode = ol.proj.fromLonLat(route[j]);
              routeNodes.push(routeNode);
              coordinates.push(routeNode);
            }
            routesFeatures.push(new ol.Feature({
              geometry: new ol.geom.LineString(routeNodes)
            }));
          }
          this.__routesSource.clear(true);
          this.__routesSource.addFeatures(routesFeatures);
          this.__routesLayer.setVisible(true);
        } else {
          this.__routesLayer.setVisible(false);
        }
        if (coordinates.length > 0) {
          var view = this.__map.getView();
          if (coordinates.length > 1) {
            view.fit(ol.extent.boundingExtent(coordinates), this.__map.getSize());
          } else {
            view.setCenter(coordinates[0]);
            view.setZoom(12);
          }
        }
      }
    },

    /**
     * Prepares qooxdoo GeoLocation and installs needed listeners.
     */
    __initGeoLocation: function () {
      var geo = qx.bom.GeoLocation.getInstance();
      geo.addListener("position", this._onGeolocationSuccess, this);
      geo.addListener("error", this._onGeolocationError, this);
    },


    /**
     * Callback function when Geolocation did work.
     */
    _onGeolocationSuccess: function (position) {
      this.zoomToPosition(position.getLongitude(), position.getLatitude(), 12, true);
      this._redrawMap();
    },


    /**
     * Callback function when GeoLocation returned an error.
     */
    _onGeolocationError: function () {
      var buttons = [];
      buttons.push(qx.locale.Manager.tr("OK"));
      var title = "Problem with Geolocation";
      var text = "Please activate location services on your browser and device.";
      qx.ui.mobile.dialog.Manager.getInstance().confirm(title, text, function () {
      }, this, buttons);
    },


    /**
     * Retrieves GeoPosition out of qx.bom.GeoLocation and zooms to this point on map.
     */
    moveToCurrentPosition: function () {
      var geo = qx.bom.GeoLocation.getInstance();
      geo.getCurrentPosition(false, 1000, 1000);
    }
  }
});
