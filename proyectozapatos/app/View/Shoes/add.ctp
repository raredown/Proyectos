<div class="shoes form">
<?php echo $this->Form->create('Shoe'); ?>
	<fieldset>
		<legend><?php echo __('Añadir Producto'); ?></legend>
	<?php
		echo $this->Form->input('precio');
		echo $this->Form->input('name');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Enviar')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Acciones'); ?></h3>
	<ul>

		<li><?php echo $this->Html->link(__('Lista Productos'), array('action' => 'index')); ?></li>
	</ul>
</div>
