/**
 * 
 */
package com.zjy.baseDao;

public class FunIMsgFormat {
	public static enum MsgStyle {
		DEFAULT_LOG("FUNI-/-DEFAULT_LOG-/-{}"), ERROR_REPORT(
				"FUNI-/-ERROR_REPORT-/-{}-/-{}");

		private String format;

		private MsgStyle() {
			this.format = "FUNI-/-{}";
		}

		private MsgStyle(String format) {
			this.format = format;
		}

		public String getFormat() {
			return this.format;
		}

		public String getFormat(String selfFormat) {
			return "FUNI-/-DEFAULT_LOG-/-" + selfFormat;
		}
	}
}
