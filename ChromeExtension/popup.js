function dumpBookmarks(query) {
  var xmlHttpRequest = new XMLHttpRequest();

  xmlHttpRequest.open("POST", "http://localhost:8080/rest/bookmark/google", true);
  xmlHttpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

  xmlHttpRequest.onreadystatechange = function() {
    if (xmlHttpRequest.readyState == 4) {
      alert("Your bookmarks was send to BookmarkShareTool");
    }
  }
  var bookmarkTreeNodes = chrome.bookmarks.getTree(
  function(bookmarkTreeNodes) {
      xmlHttpRequest.send(JSON.stringify(bookmarkTreeNodes[0]));
    });
  
}


document.addEventListener('DOMContentLoaded', function () {
  dumpBookmarks();
});