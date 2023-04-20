<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./layout/indexheader.jsp"%>

<div class="container mt-3">
	<h2>로그인페이지, 환영합니다</h2>
	<form>
		<div class="mb-3 mt-3">
			<label for="username">Username:</label> <input type="text"
				class="form-control" id="username" placeholder="Enter username"
				name="username">
		</div>

		<div class="mb-3">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" id="pwd" placeholder="Enter password"
				name="pswd">
		</div>

		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>
</div>

<script src="/js/login.js"></script>

<%@ include file="./layout/indexfooter.jsp"%>
