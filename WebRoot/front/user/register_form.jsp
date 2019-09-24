<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<link href="${pageContext.request.contextPath}/front/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/front/css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			//正则表达式
			function isEmail(val) {
				var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;
				if(!myreg.test(val))
				return '不是邮箱';
				return '是邮箱';
			};
			//邮箱验证
			function checkEmail(){
				var email=$("#txtEmail").val();
				var result=isEmail(email);
				if (result=="是邮箱") {
					$("#emailInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/label3.gif'/><font color=green>邮箱格式正确</font>");
					return true;
				}
				if (result=="不是邮箱") {
					$("#emailInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/wrong.gif'/><font color=red>邮箱格式不正确</font>");
					return false;
				}
			}
			//昵称验证
			function checkNick() {
				var nick=$("#txtNickName").val();
				var length=0;
				var code=0;
				for (var i = 0; i < nick.length; i++) {
					code=nick.charCodeAt(i);
					if (code>0&&code<127) {
						length+=1;
					}else {
						length+=2;
					}
				}
				if (length>=4&&length<=20) {
					$("#nameInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/label3.gif'/><font color=green>昵称格式正确</font>");
					return true;
				}else {
					$("#nameInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/wrong.gif'/><font color=red>昵称格式不正确</font>");
					return false;
				}
			}
			//密码验证
			function checkPass() {
				var password=$("#txtPassword").val();
				var code=0;
				for (var i = 0; i < password.length; i++) {
					code=password.charCodeAt(i);
					if (code<=0||code>=127) {
						$("#passwordInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/wrong.gif'/><font color=red>密码格式不正确</font>");
						return false;
					}
				}
				if (password.length>=6&&password.length<=20) {
					$("#passwordInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/label3.gif'/><font color=green>密码格式正确</font>");
					return true;
				}
				else{
					$("#passwordInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/wrong.gif'/><font color=red>密码格式不正确</font>");
						return false;
				}
			}
			//确认密码验证
			function checkRepeatPass() {
				var password=$("#txtPassword").val();
				var repeatPass=$("#txtRepeatPass").val();
				if (password==repeatPass) {
					$("#password1Info").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/label3.gif'/><font color=green>密码一致</font>");
					return true;
				}else{
					$("#password1Info").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/wrong.gif'/><font color=red>密码不一致</font>");
					return false;
				}
			}
			//验证码验证
			function checkCode() {
				var code=$("#txtVerifyCode").val();
				var char=0;
				for (var i = 0; i < code.length; i++) {
					char=code.charCodeAt(i);
					if (char<=0||char>=127) {
						$("#codeInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/wrong.gif'/><font color=red>验证码格式不正确</font>");
						return false;
					}
				}
				if (code.length==4) {
					$("#codeInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/label3.gif'/><font color=green>验证码格式正确</font>");
					return true;
				}else {
					$("#codeInfo").html("<img width='15px' src='${pageContext.request.contextPath}/front/images/wrong.gif'/><font color=red>验证码格式不正确</font>");
					return false;
				}
			}
			//表单验证
			function checkFrom() {
				if (checkEmail()&&checkNick()&&checkPass()&&checkRepeatPass()&&checkCode()) {
					$("#btnClientRegister").prop("type","submit");
				}else {
					$("#btnClientRegister").prop("type","button");
				}
			}
		</script>
	</head>
	<body>
		/<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			注册步骤:
			<span class="red_bold">1.填写信息</span> > 2.验证邮箱 > 3.注册成功
		</div>
		<div class="fill_message">
			<form name="ctl00" method="post" action="${pageContext.request.contextPath}/user/regist" id="f" >
				<h2>
					以下均为必填项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${message}
				</h2>
				<table class="tab_login" >
					<tr>
						<td valign="top" class="w1">
							请填写您的Email地址：
						</td>
						<td>
							<input name="user.email" onblur="checkEmail()" type="text" id="txtEmail" class="text_input"/>
							<div class="text_left" id="emailValidMsg">
								<p>
									请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。
								</p>
								<span id="emailInfo"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置您在当当网的昵称：
						</td>
						<td>
							<input name="user.nickname" onblur="checkNick()" type="text" id="txtNickName" class="text_input" />
							<div class="text_left" id="nickNameValidMsg">
								<p>
									您的昵称可以由小写英文字母、中文、数字组成，
								</p>
								<p>
									长度4－20个字符，一个汉字为两个字符。
								</p>
								<span id="nameInfo"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置密码：
						</td>
						<td>
							<input name="user.password" onblur="checkPass()" type="password" id="txtPassword" class="text_input" />
							<div class="text_left" id="passwordValidMsg">
								<p>
									您的密码可以由大小写英文字母、数字组成，长度6－20位。
								</p>
								<span id="passwordInfo"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							再次输入您设置的密码：
						</td>
						<td>
							<input name="password" onblur="checkRepeatPass()" type="password" id="txtRepeatPass" class="text_input"/>
							
							<div class="text_left" id="repeatPassValidMsg">
								<span id="password1Info"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							验证码：
						</td>
						<td>
							<img class="yzm_img" id='imgVcode' src="${pageContext.request.contextPath}/admin/code" />
							<input name="code" onblur="checkCode()" type="text" id="txtVerifyCode" class="yzm_input"/>
							<div class="text_left t1">
								<p class="t1">
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>
									<a href="#" id="change" >看不清楚？换个图片</a>
									<br/>
									<span id="codeInfo"></span>
								</p>
							</div>
						</td>
					</tr>
				</table>
				<div class="login_in">
					<input id="btnClientRegister" onclick="checkFrom()" class="button_1" name="submit"  type="button" value="注 册"/>
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

