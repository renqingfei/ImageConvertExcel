<%@page language="java"  contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>多张图片上传</title>
		<style>
			.main {
				width: 80%;
				margin: auto;
			}
			
			* {
				padding: 0;
				margin: 0;
			}
			
			.btn {
				padding: 9px 18px;
				background: #40AFFE;
				color: #FFFFFF;
				border-radius: 5px;
			}
			
			.upimg {
				position: relative;
				width: 100px;
				height: 100px;
				border-radius: 5px;
				border: dashed #999999;
				background: url(img/addimg.svg) no-repeat;
				background-position: 33px;
			}
			
			.upimg input {
				position: absolute;
				width: 100px;
				height: 100px;
				opacity: 0;
			}
			
			#showui {
				display: flex;
				justify-content: flex-start;
			}
			
			#showui li {
				width: 150px;
				height: 150px;
				position: relative;
				overflow: hidden;
				display: inline-block;
				margin-right: 5px;
			}
			
			#showui li img.showimg {
				position: absolute;
				text-align: center;
				top: 50%;
				left: 50%;
				transform: translate(-50%, -50%);
				z-index: 6;
			}
			
			.showdiv {
				position: absolute;
				z-index: 9;
				bottom: 0;
				width: calc(100% - 20px);
				padding: 10px;
				display: flex;
				justify-content: space-around;
				background: rgba(0, 0, 0, .6);
			}
			
			.showdiv img {
				width: 20px;
				height: 20px;
				cursor: pointer;
			}
			
			#showui li:first-child img.left {
				opacity: .6;
				cursor: no-drop;
			}
			
			#showui li:last-child img.right {
				opacity: .6;
				cursor: no-drop;
			}
			
			.oneright {
				opacity: .6;
				cursor: no-drop !important;
			}
		</style>
	</head>

	<body>
		<div class="main">
			<div id="showimg">
				<ul id="showui">
				</ul>

				<div id="showinput">

				</div>
			</div>
			<div class="upimg">
				<input type="file" id="upgteimg" value="" multiple />
			</div>

			<button class="btn" id="submit">开始上传</button>
			<div id = "pic"></div>
		</div>
		<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>

		<script type="text/javascript" src="js/updateimg.js" ></script>
</body>

</html>