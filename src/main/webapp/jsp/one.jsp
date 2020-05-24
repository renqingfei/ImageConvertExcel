<%@page language="java"  contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>单张图片上传</title>

</head>

<body>
<form action="/uploadOne" method="post" enctype="multipart/form-data">
<input type="file" name="file">
<input type="submit">
</form>
</body>

</html>