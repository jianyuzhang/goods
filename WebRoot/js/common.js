function _change() {
	$("#vCode").attr("src", "/goods/operate/user/VerifyCode.do?a=" + new Date().getTime());
}