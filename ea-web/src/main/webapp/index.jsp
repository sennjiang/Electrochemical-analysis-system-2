<html>
<body>
<h2>Hello World!</h2>
<form action="/user/login" method="post" >
    <input type="text" name="account"/>
    <input type="password" name="password"/>
    <button type="submit">login</button>
</form>

<h3>form</h3>
<form action="/user/file/test" method="post" enctype="multipart/form-data">
    <input type="text" name="boundary" placeholder="0205"/>
    <input type="file" name="upload"><br>
    <input type="submit" value="submit"/>
</form><br>
</body>
</html>
