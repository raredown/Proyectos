<?php
$cakeDescription = __d('cake_dev', 'Zapateria Retagón');
?>
<!DOCTYPE html>
<html>
<head>
	<?php echo $this->Html->charset(); ?>
	<title>
		<?php echo $cakeDescription ?>
	</title>
	<?php
		echo $this->Html->meta('icon');
		echo $this->Html->css('cake.generic');
		echo $this->fetch('meta');
		echo $this->fetch('css');
		echo $this->fetch('script');
	?>
	<script type="text/javascript">
		var ProjectURL="<?php echo Router::url("/",true)?>";
    </script>
	<meta name="Description" content="Esta página gestiona las altas, bajas y modificaciones de 	una tienda de zapatos">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<?php
	$faviconurl= Router::url("/")."img/favicon.ico";
	?>
	<link rel="shortcut icon" href="<?php echo $faviconurl;?>">
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.0/jquery.js" type="text/javascript"></script>	
     <?php echo $this->Html->script('mi'); ?>
</head>
<body>
	<div id="content" class="cuerpo">
		<?php echo $this->Session->flash(); ?>
		<?php echo $this->fetch('content'); ?>
	</div>
		<?php echo $this->element('sql_dump'); ?>
	</div>
</body>
</html>