<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 90%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body> 
<a href="addBook"> Add Book </a>
<table id="customers">
<tr>
<th> #  </th>
<th> Title </th>
<th> Author   </th>
<th> ISBN-13    </th>
<th> Comments  </th>
<th>     </th>
<th>     </th>
</tr>
<c:forEach var="book" items="${bookCommentDto}">
<tr>
<td> <c:out value="${book.id}"></c:out>   </td>
<td> <c:out value="${book.title}"></c:out>   </td>
<td> <c:out value="${book.author}"></c:out>   </td>
<td> <c:out value="${book.isbn}"></c:out>   </td>
<td> <c:out value="${book.commentCount}"></c:out>   </td>
<td> <a href="edit?id=${book.id}"> Edit </a></td>
<td> <a href="delete?id=${book.id}"> Delete </a>   </td>
</tr>
</c:forEach>
</table>


<script type="text/javascript">
jQuery(function($) {
    var items = $("table tbody tr");

    var numItems = items.length;
    var perPage = 10;
    items.slice(perPage).hide();
    $(".pagination-page").pagination({
        items: numItems,
        itemsOnPage: perPage,
        cssStyle: "light-theme",
        onPageClick: function(pageNumber) {
            var showFrom = perPage * (pageNumber - 1);
            var showTo = showFrom + perPage;
            items.hide()
                 .slice(showFrom, showTo).show();
        }
    });

    function checkFragment() {
        var hash = window.location.hash || "#page-1";
        hash = hash.match(/^#page-(\d+)$/);
        if(hash) {
            $(".pagination-page").pagination("selectPage", parseInt(hash[1]));
        }
    };
    $(window).bind("popstate", checkFragment);
    checkFragment();

});
</script>
</body>
</html>
