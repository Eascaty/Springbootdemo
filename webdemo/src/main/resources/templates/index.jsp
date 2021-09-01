<%@ page contentType="text/html; ISO-8859-1" language="java" %>

<html>
<head>
    <title>全局刷新实现bmi计算</title>
</head>
<body>
<div align="center">
    <p>计算Bmi</p>
    <form action="/bmiservlet" method="get">
        姓名：<input type="text" name="name">  <br/>
        体重：<input type="text" name="w">  <br/>
        身高：<input type="text" name="h">  <br/>
        操作：<input type="submit" value="计算bmi">  <br/>
    </form>
</div>

</body>
</html>