
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<p> Welcome b2a ya ${it.name} </p>
<p> This is should be user home page </p>
<p> Current implemented services "http://fci-swe-apps.appspot.com/rest/RegistrationService --- {requires: uname, email, password}" </p>
<p> and "http://fci-swe-apps.appspot.com/rest/LoginService --- {requires: uname,  password}" </p>
<p> you should implement sendFriendRequest service and addFriend service
<form action="/social/sendrequest" method="post">
  Email : <input type="email" name="email" /> <br>
  Emai2 : <input type="email" name="email2" /> <br>
  <input type="submit" value="Add Friend">
</form>
<form action="/social/searchUser" method="post">
  Name : <input type="email" name="email" /> <br>
  <input type="submit" value="Search">
</form>
<form action="/social/notify" method="post">
  <input type="submit" value="Notification">
</form>
<a href ="/social/login/">Logout</a>
<a href ="/social/accept/">Accept Friend</a>
<form action="/social/newpage" method="post">
  Owner : <input type="text" name="owner" /> <br>
  name of page : <input type="text" name="name" /> <br>
  cateagory : <input type="text" name="cateagory" /> <br>
  <input type="submit" value="create">
  <form action="/social/newpost" method="post">
  User : <input type="text" name="user" /> <br>
  User ID : <input type="text" name="UID" /> <br>
  Feeling : <input type="text" name="feeling" /> <br>
  content of post : <input type="text" name="content" /> <br>
  type of post : <input type="text" name="type" /> <br>
  <input type="submit" value="Post">
    <form action="/social/notify" method="post">
  User : <input type="text" name="user" /> <br>
  User ID : <input type="text" name="UID" /> <br>
  <input type="submit" value="get notification">
</form>
</body>
</html>