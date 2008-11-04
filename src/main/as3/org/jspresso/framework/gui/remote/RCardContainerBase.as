/**
 * Generated by Gas3 v1.1.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (RCardContainer.as).
 */

package org.jspresso.framework.gui.remote {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import org.granite.collections.IMap;

    [Bindable]
    public class RCardContainerBase extends RContainer {

        private var _cardMap:IMap;
        private var _selectedCard:String;

        public function set cardMap(value:IMap):void {
            _cardMap = value;
        }
        public function get cardMap():IMap {
            return _cardMap;
        }

        public function set selectedCard(value:String):void {
            _selectedCard = value;
        }
        public function get selectedCard():String {
            return _selectedCard;
        }

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _cardMap = input.readObject() as IMap;
            _selectedCard = input.readObject() as String;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_cardMap);
            output.writeObject(_selectedCard);
        }
    }
}