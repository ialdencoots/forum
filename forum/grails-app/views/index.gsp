<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			
			body{
				background-color:lightblue;
				height:100%;
			}
			
			.ie6 {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#body h1 {
				font-family: helvetica;
				font-size:3em;
				color:white;
				font-weight:bold;
			}
			
			#body h1 a {
				color:white;
				text-decoration:none;
				background-color:pink;
				
			}
			#body h1 a:hover {
				color:white;
				background-color:lightblue;
				
			}
				
		</style>
	</head>
	<body>
		<div id="body">
		<center><h1>DO you want to <a href="/forum/login/auth">LOGIN</a> or <a href="/forum/forumUser/create">SIGNUP?</a></h1></center>

		
		<center><p><h1>Neither? Then just checkout the diffrent <a href="/forum/forumTopic/list/">TOPICS</a> </h1></p></center>
		
		</div>
	</body>
</html>
