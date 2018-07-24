package org.example.test.andromda.model {
	
	[Bindable]
	public class ValueObject {
		public var id:String;
		
		public function ValueObject() {
			super();
		}
		
		public function copyFrom(object:Object):void {
			throw Error("Should be overriden");
		}
	}
}