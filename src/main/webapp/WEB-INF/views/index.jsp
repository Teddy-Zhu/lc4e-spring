<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="alternate icon" type="image/png" href="images/logo.png">
<title>Light Community</title>
<meta name="description" content="Light Community For EveryOne" />
<meta name="keywords" content="html5, ui, library, framework, javascript" />
<link rel="stylesheet" type="text/css" class="ui" href="plugins/semantic-ui/semantic.min.css">
<link rel="stylesheet" type="text/css" class="ui" href="plugins/lc4e/jquery-extend.css">
<link rel="stylesheet" type="text/css" class="ui" href="css/view/index.css">
</head>
<body>
	<!-- Header Menu Start -->
	<div id="menu" class="ui secondary menu large page grid">
		<div class="column">
			<div class="hidden-pc">
				<a class="item linked"> <i class="content icon"></i> Menus
				</a>
			</div>
			<div class="left menu">
				<img class="logo ui image item hidden-mb" src="images/logo.png"> <a class="item linked"> <i class="home icon"></i> Home
				</a> <a class="item linked"> <i class="mail icon"></i> Messages
				</a> <a class="item linked"> <i class="user icon"></i> Friends
				</a>
				<div class="ui dropdown link item">
					<i class="font icon"></i><span class="text">Language</span><i class="dropdown icon"></i>
					<div class="menu">
						<a class="item">C/C++</a> <a class="item">Java</a> <a class="item">JavaScript</a>
						<div class="ui link item">
							<span class="text">Script</span><i class="dropdown icon"></i>
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
				<div class="ui item animated fade button">
					<div class="visible content">Sign Up</div>
					<div class="hidden content">
						<i class="add user icon"></i>
					</div>
				</div>
				<div class="ui item animated button">
					<div class="visible content">Sign In</div>
					<div class="hidden content">
						<i class="user icon"></i>
					</div>
				</div>
				<div id="expendHeader" class="ui item hidden-mb">
					<div class="ui linked label">
						<i class="maximize icon"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Heard Menu End -->
	<!-- Content Start -->
	<div id="content">
		<div id="mainContent" class="ui page  grid">
			<div id="leftContent" class="ten wide column">
				<div class="ui divided items">
					<div class="item">
						<div class="ui label">AnnounceMent</div>
						<div id="announce" class="ui text shape">
							<div class="sides">
								<div class="active ui header side">Did you know? This side starts visible.</div>
								<div class="ui header side">Help, its another side!</div>
								<div class="ui header side">This is the last side</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="ui fluid tiny image">
							<div class="ui teal ribbon label hidden-mb">User</div>
							<img src="/images/wireframe/image.png">
						</div>
						<div class="content">
							<a class="header">The friction between your thoughts and your code</a>
							<div class="extra">
								<div class="ui label">IMAX</div>
								<div class="ui label">
									<i class="globe icon"></i> Additional Languages
								</div>
								<a class="ui red image right floated label"><i class="ban icon"></i>block </a>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="ui fluid tiny image">
							<div class="ui teal ribbon label hidden-mb">Admin</div>
							<img src="/images/wireframe/image.png">
						</div>
						<div class="content">
							<a class="header">SEND AND RECEIVE POST REQUESTS WITH PHP</a>
							<div class="extra">
								<div class="ui label">IMAX</div>
								<div class="ui label">
									<i class="globe icon"></i> Additional Languages
								</div>
								<a class="ui red image right floated label"><i class="ban icon"></i>block </a>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="ui fluid tiny image">
							<div class="ui teal ribbon label hidden-mb">Admin</div>
							<img src="/images/wireframe/image.png">
						</div>
						<div class="content">
							<a class="header">How to build SkyEye staticly</a>
							<div class="extra">
								<div class="ui label">IMAX</div>
								<div class="ui label">
									<i class="globe icon"></i> Additional Languages
								</div>
								<a class="ui red image right floated label"><i class="ban icon"></i>block </a>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="ui fluid tiny image">
							<div class="ui teal ribbon label hidden-mb">Admin</div>
							<img src="/images/wireframe/image.png">
						</div>
						<div class="content">
							<a class="header">Manipulating Linux command line output</a>
							<div class="extra">
								<div class="ui label">IMAX</div>
								<div class="ui label">
									<i class="globe icon"></i> Additional Languages
								</div>
								<a class="ui red image right floated label"><i class="ban icon"></i>block </a>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="ui fluid tiny image">
							<div class="ui teal ribbon label hidden-mb">Admin</div>
							<img src="/images/wireframe/image.png">
						</div>
						<div class="content">
							<a class="header">Mastering Linux vi editor</a>
							<div class="extra">
								<div class="ui label">IMAX</div>
								<div class="ui label">
									<i class="globe icon"></i> Additional Languages
								</div>
								<a class="ui red image right floated label"><i class="ban icon"></i>block </a>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="ui fluid tiny image">
							<div class="ui teal ribbon label hidden-mb">Admin</div>
							<img src="/images/wireframe/image.png">
						</div>
						<div class="content">
							<a class="header">LIST OF FAVORITE OS X WEB DEVELOPER APPS</a>
							<div class="extra">
								<div class="ui label">IMAX</div>
								<div class="ui label">
									<i class="globe icon"></i> Additional Languages
								</div>
								<a class="ui red image right floated label"><i class="ban icon"></i>block </a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="rightContent" class="six wide column">Test</div>
		</div>

		<div class="ui inverted black footer vertical segment">
			<div class="container">
				<div class="ui stackable inverted divided relaxed grid">
					<div class="eight wide column">
						<h3 class="ui inverted header">Light Community</h3>
						<p>Designed By ZhuXi.Drived by Semantic UI.</p>
						<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
							<input type="hidden" name="cmd" value="_s-xclick"> <input type="hidden" name="hosted_button_id" value="7ZAF2Q8DBZAQL">
							<button type="submit" class="ui teal button">Donate Semantic</button>
						</form>
					</div>
					<div class="four wide column">
						<h5 class="ui teal inverted header">Contributers</h5>
						<div class="ui inverted link list">
							<a class="item" href="http://www.lc4e.com/" target="_blank">ZhuXi</a>
						</div>
					</div>
					<div class="four wide column">
						<h5 class="ui teal inverted header">LC4E Network</h5>
						<div class="ui inverted link list">
							<a class="item" href="http://coding.net">Coding.Net</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Content  End -->
	<!-- Slider Bar Start -->
	<div id="config-tool" class="closed">
		<a id="config-tool-cog"> <i class="large setting loading icon"></i>
		</a>
		<div id="config-tool-options" class="ui segment">
			<h4 class="ui header">
				Area List<i class="linked angle double left icon transition hidden"></i>
			</h4>
			<div id="menu1" class="ui animated selection list">
				<div class="item" data-target="#menu2">
					Fixed Header<i class="angle double right icon"></i>
				</div>
				<div class="item">
					<div class="ui slider checkbox">
						<input type="checkbox" name="public"> <label>Fixed Left Menu</label>
					</div>
				</div>
				<div class="item">
					<div id="fixFooter" class="ui toggle checkbox">
						<input type="checkbox" name="public"> <label>Fixed Footer</label>
					</div>
				</div>
				<div class="item">
					<div class="ui checkbox">
						<input type="checkbox" name="public"> <label>Boxed Layout</label>
					</div>
				</div>
				<div class="item">
					<div class="ui toggle red checkbox">
						<input type="checkbox" name="public"> <label>Right-to-Left</label>
					</div>
				</div>
			</div>
			<div id="menu2" data-parent="#menu1" class="ui animated selection list transition hidden">
				<div class="item">second select</div>
				<div class="item">second select</div>
				<div class="item" data-target="#menu3">
					third select <i class="angle double right icon"></i>
				</div>
			</div>
			<div id="menu3" data-parent="#menu2" class="ui animated selection list transition hidden">
				<div class="item">third select11</div>
			</div>
			<h4 class="ui header">Skin Color</h4>
			<div class="ui list">
				<a class="ui red label"></a> <a class="ui teal label"></a> <a class="ui blue label"></a> <a class="ui purple label"></a> <a class="ui green label"></a> <a class="ui red label"></a>
			</div>
		</div>
	</div>
	<!-- Slider Bar End -->
	<script type="text/javascript" src="plugins/jquery/jquery.js"></script>
	<script type="text/javascript" src="plugins/semantic-ui/semantic.min.js"></script>
	<script type="text/javascript" src="plugins/lc4e/jquery-extend.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>
</html>