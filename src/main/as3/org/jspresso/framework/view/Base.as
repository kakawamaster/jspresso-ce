/**
 * Generated by Gas3 v1.1.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (.as).
 */

package org.jspresso.framework.view {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.jspresso.framework.action.IActionHandler;

    [Bindable]
    public class Base implements IExternalizable {

        private var _this$0:AbstractViewFactory;
        private var _val$actionHandler:IActionHandler;
        private var _val$cardView:IMapView;

        public function readExternal(input:IDataInput):void {
            _this$0 = input.readObject() as AbstractViewFactory;
            _val$actionHandler = input.readObject() as IActionHandler;
            _val$cardView = input.readObject() as IMapView;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_this$0);
            output.writeObject(_val$actionHandler);
            output.writeObject(_val$cardView);
        }
    }
}