<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="alternate icon" type="image/png" href="images/logo.png">
<title>Index</title>
<meta name="description" content="Light Community For EveryOne" />
<meta name="keywords" content="html5, ui, library, framework, javascript" />
<link rel="stylesheet" type="text/css" class="ui" href="plugins/semantic-ui/semantic.min.css">
<link rel="stylesheet" type="text/css" class="ui" href="plugins/lc4e/jquery-extend.css">
<link rel="stylesheet" type="text/css" class="ui" href="css/view/index.css">

</head>
<body>
	<div id="menu" class="ui secondary menu large page grid">
		<div class="column">
			<div class="left menu">
				<img class="logo ui image item hidden-mobile" src="images/logo.png"> <a class="item linked"> <i class="home icon"></i> Home
				</a> <a class="item linked"> <i class="mail icon"></i> Messages
				</a> <a class="item linked"> <i class="user icon"></i> Friends
				</a>
				<div class="ui dropdown link item">
					<i class="font icon"></i><span class="text">Language</span>
					<div class="menu">
						<a class="item">C/C++</a> <a class="item">Java</a> <a class="item">JavaScript</a>
						<div class="item">
							<i class="dropdown icon"></i> <span class="text">Script</span>
							<div class="menu">
								<div class="item">Python</div>
								<div class="divider"></div>
								<div class="item">ruby</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="right menu">
				<div class="item">
					<div class="ui icon input">
						<input id="searchSite" type="text" placeholder="Search..."> <i class="search link icon"></i>
					</div>
				</div>
				<a class="ui item linked"> Logout </a>
			</div>
		</div>
	</div>
	<div class="ui two column centered grid">
		<div class="one column row"></div>
		<div class="one column">
			<div class="one centered column">
				<div id="testLogin" class="ui button">testLogin</div>
				<div id="testToken" class="ui teal basic button">testToken</div>
				<div id="testLoader" class="ui black button">testLoader</div>
			</div>
			<h4 class="ui horizontal header divider">
				<i class="bar chart icon"></i> Functions
			</h4>
			<div class="one centered column">
				<div id="testModal" class="ui negative button">testModal</div>
				<div class="ui buttons">
					<div id="testAttachedProgress" class="ui positive button">testAttachedProgress</div>
					<div class="or" data-text="OR"></div>
					<div id="testProgress" class="ui primary button">testProgress</div>
				</div>
			</div>
		</div>
		<div class="centered column">
			<div id="idcard" class="ui card">
				<div class="image">
					<img src="/images/wireframe/image.png">
				</div>
				<div class="content">
					<a class="header">Project</a>
					<div class="meta">
						<span class="date">Started in 2015</span>
					</div>
				</div>
				<div class="extra content">
					<a> <i class="user icon"></i> 22 Friends
					</a>
				</div>
			</div>
		</div>
		<div class="centered column">
			<div id="idcard" class="ui card">
				<div class="image">
					<img src="/images/wireframe/image.png">
				</div>
				<div class="content">
					<a class="header">Project</a>
					<div class="meta">
						<span class="date">Started in 2015</span>
					</div>
				</div>
				<div class="extra content">
					<a> <i class="user icon"></i> 22 Friends
					</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="plugins/jquery/jquery.js"></script>
	<script type="text/javascript" src="plugins/semantic-ui/semantic.min.js"></script>
	<script type="text/javascript" src="plugins/lc4e/jquery-extend.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>
</html>