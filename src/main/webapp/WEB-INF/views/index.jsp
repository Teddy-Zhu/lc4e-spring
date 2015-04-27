<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="alternate icon" type="image/png" href="images/logo.png">
<title>Light Community</title>
<link rel="stylesheet" type="text/css" class="ui" href="plugins/semantic-ui/1.12.0/semantic.min.css">
<link rel="stylesheet" type="text/css" class="ui" href="js/lc4e/jquery-extend.css">
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
				<div id="userItem" class="item">
					<img class="ui headered linked image" src="/images/wireframe/image.png">
					<div class="floating circular ui red label">22</div>
					<div id="userCardPop" class="ui flowing popup">
						<div id="userCard" class="ui card">
							<div class="content">
								<div class="centered aligned header">Admin</div>
								<div class="ui clearing divider"></div>
								<div class="description">
									<div class="ui divided items">
										<div class="item">
											<i class="comments outline icon"></i> Comments <a class="ui right floated circular label"> 11 </a>
										</div>
										<div class="item">
											<i class="diamond icon"></i> Diamonds <a class="ui right floated circular label"> 111 </a>
										</div>
										<div class="item">
											<i class="mail outline icon"></i> Messages <a class="ui  right floated circular label"> 2111 </a>
										</div>
									</div>
								</div>
							</div>
							<div class="extra content">
								<span class="left floated"> <i class="users icon"></i> Follows <a class="ui transparent circular label"> 10 </a>
								</span> <span class="right floated"> <i class="star icon"></i> Favorites <a class="ui transparent circular label"> 5 </a>
								</span>
							</div>
							<div class="ui bottom attached button">
								<i class="setting icon"></i> Settings
							</div>
						</div>
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
				<div id="announcement">
					<div class="item">
						<div class="ui label">
							<i class="announcement icon"></i>
							<div class="hidden-mb">AnnounceMent</div>
						</div>
						<div id="announce" class="ui text shape">
							<div class="sides">
								<div class="active ui header side">Did you know? This side starts visible.</div>
								<div class="ui header side">Help, its another side!</div>
								<div class="ui header side">This is the last side</div>
							</div>
						</div>
					</div>
				</div>
				<div id="articlelist" class="ui nobox segment">
					<div class="ui divided items"></div>
				</div>
				<div id="articlebottons" class="ui segment">
					<div id="prePage" class="ui left floated labeled icon button">
						<i class="angle double left icon"></i> <spring:message code="index.preBtn"/>
					</div>
					<div id="nextPage" class="ui right floated right labeled icon button">
						<i class="angle double right icon"></i> <spring:message code="index.nextBtn"/>
					</div>

				</div>

			</div>
			<div id="rightContent" class="six wide column">
				<div id="todayHot" class="ui sticky segment">
					<h4 class="ui horizontal header divider">
						<i class="bar chart icon"></i> Today HotSpot
					</h4>
					<div class="ui divided items"></div>
				</div>
				<div class="ui vertical rectangle test ad" data-text="Advertisement"></div>
			</div>
		</div>
	</div>
	<!-- Content  End -->
	<!-- Right Buttons Start -->
	<a id="GTTop" href="#"><i class="circular large arrow up icon"></i></a>
	<!-- Right Buttons End -->
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
					<div id="boxedLayout" class="ui checkbox">
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
	<script src="plugins/require.js/2.1.17/require.min.js" data-main="js/index"></script>
</body>
</html>