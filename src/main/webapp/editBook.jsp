<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Book </title>
<style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>


<table>
<tr>
<td>
<h3>Edit Book</h3>
<div style="">
  <form action="/updateBook"  method="post">
    <label for="Title">Title</label><br>
    <input type="text" id="title" required="required" name="title" value="${book.title }" placeholder="Title"><br>
   <input type="hidden" id="id" name="id" value="${book.id }">
    <label for="Author">Author</label><br>
    <input type="text" id="author" required="required" name="author"  value="${book.author }" placeholder="Author"><br>

   <label for="Isbn">Isbn</label><br>
    <input type="text" id="author" required="required" name="isbn"  value="${book.isbn }" placeholder="Isbn"><br>
  
    <input style="width: 50%" type="submit" value="Submit">
  </form>
</div>
</td>
<td>
<h3>Comments</h3>
<div style="">
  <form action="/addComment"  method="post">
    <label for="Title">Comment</label><br>
    <input  type="text" required="required" style="    width: 204px;  height: 70px;" id="comment" name="comment" /><br>
  	<input type="hidden" id="id"  name="bookId" value="${book.id }">
    <input style="width: 50%" type="submit" value="Submit">
  </form>
  <c:forEach var="c" items="${comments}">
  <c:out value="${c.date }"></c:out><br>
  <c:out value="${c.comment }"></c:out><br><br>
  </c:forEach>
</div>
</td>
</tr>
</table>


</body>
</html>