/*
 * Copyright (c) 2005-2016 Vincent Vandenschrick. All rights reserved.
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
qx.Class.define("org.jspresso.framework.view.qx.MapComponent", {
  extend: qx.ui.core.Widget,

  include: [org.jspresso.framework.view.qx.MMapMixin],

  construct: function () {
    this.base(arguments);
    this.addListenerOnce("appear", this._initializeMap, this);
    this.addListener("resize", this._redrawMap, this);
  },


  members: {

    _getMapDomTarget: function () {
      return this.getContentElement().getDomElement();
    },

    showMap: function () {
      var redraw = false;
      if (this.getVisibility() != "visible") {
        redraw = true;
      }
      this.show();
      if (redraw) {
        qx.event.Timer.once(this._redrawMap, this, 100);
      }
    },

    hideMap: function () {
      this.hide()
    }

  }
});
