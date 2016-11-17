<div class="shoes form">
<?php echo $this->Form->create('Shoe'); ?>
	<fieldset>
		<legend><?php echo __('Editar Producto'); ?></legend>
	<?php
		echo $this->Form->input('id');
		echo $this->Form->input('precio');
		echo $this->Form->input('name');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Enviar')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Acciones'); ?></h3>
	<ul>

		<li><?php echo $this->Form->postLink(__('Borrar'), array('action' => 'delete', $this->Form->value('Shoe.id')), null, __('Estas seguro de eliminarlo # %s?', $this->Form->value('Shoe.id'))); ?></li>
		<li><?php echo $this->Html->link(__('Lista Productos'), array('action' => 'index')); ?></li>
	</ul>
</div>
