/**
 * Copyright (c) 2005-2008 Vincent Vandenschrick. All rights reserved.
 * <p>
 * This file is part of the Jspresso framework. Jspresso is free software: you
 * can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version. Jspresso is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with Jspresso. If not, see <http://www.gnu.org/licenses/>.
 */


package org.jspresso.framework.model.descriptor.basic {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import mx.collections.ListCollectionView;
    import org.jspresso.framework.model.descriptor.IComponentDescriptor;
    import org.jspresso.framework.util.descriptor.DefaultIconDescriptor;

    [Bindable]
    [RemoteClass(alias="org.jspresso.framework.model.descriptor.basic.AbstractComponentDescriptor")]
    public class AbstractComponentDescriptor extends DefaultIconDescriptor implements IComponentDescriptor {

        private var _ancestorDescriptors:ListCollectionView;
        private var _componentContract:Class;
        private var _grantedRoles:ListCollectionView;
        private var _lifecycleInterceptors:ListCollectionView;
        private var _nestedPropertyDescriptors:Object;
        private var _orderingProperties:ListCollectionView;
        private var _propertyDescriptorsMap:Object;
        private var _queryableProperties:ListCollectionView;
        private var _renderedProperties:ListCollectionView;
        private var _serviceContracts:ListCollectionView;
        private var _serviceDelegates:Object;
        private var _tempPropertyBuffer:ListCollectionView;
        private var _toStringProperty:String;
        private var _unclonedProperties:ListCollectionView;

        public function set ancestorDescriptors(value:ListCollectionView):void {
            _ancestorDescriptors = value;
        }
        public function get ancestorDescriptors():ListCollectionView {
            return _ancestorDescriptors;
        }

        public function get componentContract():Class {
            return _componentContract;
        }

        public function set grantedRoles(value:ListCollectionView):void {
            _grantedRoles = value;
        }
        public function get grantedRoles():ListCollectionView {
            return _grantedRoles;
        }

        public function set lifecycleInterceptors(value:ListCollectionView):void {
            _lifecycleInterceptors = value;
        }
        public function get lifecycleInterceptors():ListCollectionView {
            return _lifecycleInterceptors;
        }

        public function set orderingProperties(value:ListCollectionView):void {
            _orderingProperties = value;
        }
        public function get orderingProperties():ListCollectionView {
            return _orderingProperties;
        }

        public function set queryableProperties(value:ListCollectionView):void {
            _queryableProperties = value;
        }
        public function get queryableProperties():ListCollectionView {
            return _queryableProperties;
        }

        public function set renderedProperties(value:ListCollectionView):void {
            _renderedProperties = value;
        }
        public function get renderedProperties():ListCollectionView {
            return _renderedProperties;
        }

        public function get serviceContracts():ListCollectionView {
            return _serviceContracts;
        }

        public function set serviceDelegates(value:Object):void {
            _serviceDelegates = value;
        }

        public function set toStringProperty(value:String):void {
            _toStringProperty = value;
        }
        public function get toStringProperty():String {
            return _toStringProperty;
        }

        public function set unclonedProperties(value:ListCollectionView):void {
            _unclonedProperties = value;
        }
        public function get unclonedProperties():ListCollectionView {
            return _unclonedProperties;
        }

        public function get computed():Boolean {
            return false;
        }

        public function get declaredPropertyDescriptors():ListCollectionView {
            return null;
        }

        public function get entity():Boolean {
            return false;
        }

        public function get modelType():Class {
            return null;
        }

        public function get propertyDescriptors():ListCollectionView {
            return null;
        }

        public function get purelyAbstract():Boolean {
            return false;
        }

        public function get queryComponentContract():Class {
            return null;
        }

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _ancestorDescriptors = input.readObject() as ListCollectionView;
            _componentContract = input.readObject() as Class;
            _grantedRoles = input.readObject() as ListCollectionView;
            _lifecycleInterceptors = input.readObject() as ListCollectionView;
            _nestedPropertyDescriptors = input.readObject() as Object;
            _orderingProperties = input.readObject() as ListCollectionView;
            _propertyDescriptorsMap = input.readObject() as Object;
            _queryableProperties = input.readObject() as ListCollectionView;
            _renderedProperties = input.readObject() as ListCollectionView;
            _serviceContracts = input.readObject() as ListCollectionView;
            _serviceDelegates = input.readObject() as Object;
            _tempPropertyBuffer = input.readObject() as ListCollectionView;
            _toStringProperty = input.readObject() as String;
            _unclonedProperties = input.readObject() as ListCollectionView;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_ancestorDescriptors);
            output.writeObject(_componentContract);
            output.writeObject(_grantedRoles);
            output.writeObject(_lifecycleInterceptors);
            output.writeObject(_nestedPropertyDescriptors);
            output.writeObject(_orderingProperties);
            output.writeObject(_propertyDescriptorsMap);
            output.writeObject(_queryableProperties);
            output.writeObject(_renderedProperties);
            output.writeObject(_serviceContracts);
            output.writeObject(_serviceDelegates);
            output.writeObject(_tempPropertyBuffer);
            output.writeObject(_toStringProperty);
            output.writeObject(_unclonedProperties);
        }
    }
}