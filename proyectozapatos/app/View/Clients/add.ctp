<div class="clients form">
<?php echo $this->Form->create('Client'); ?>
	<fieldset>
		<legend><?php echo __('Añadir Cliente'); ?></legend>
	<?php
		echo $this->Form->input('name');
		echo $this->Form->input('apellidos');
		echo $this->Form->input('dni');
		echo $this->Form->input('telefono');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Enviar')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Acciones'); ?></h3>
	<ul>

		<li><?php echo $this->Html->link(__('Lista Clientes'), array('action' => 'index')); ?></li>
	</ul>
</div>
