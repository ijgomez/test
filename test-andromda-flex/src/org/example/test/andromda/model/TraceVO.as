package org.example.test.andromda.model {
	
	[RemoteClass(alias="org.example.test.andromda.vo.TraceVO")]
	[Bindable]
	public class TraceVO extends ValueObject {
		
		public var dateTime:Date;
		public var type:String;
		public var message:String;
		
		public override function copyFrom(object:Object):void {
			var form:TraceVO = object as TraceVO;
			
			this.id = form.id;
			this.dateTime = form.dateTime;
			this.type = form.type;
			this.message = form.message;
		}

	}
}