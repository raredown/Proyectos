<div class="shoes view">
<h2><?php echo __('Productos'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($shoe['Shoe']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Precio'); ?></dt>
		<dd>
			<?php echo h($shoe['Shoe']['precio']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Nombre'); ?></dt>
		<dd>
			<?php echo h($shoe['Shoe']['name']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Creación'); ?></dt>
		<dd>
			<?php echo h($shoe['Shoe']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modificación'); ?></dt>
		<dd>
			<?php echo h($shoe['Shoe']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Acciones'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Editar Producto'), array('action' => 'edit', $shoe['Shoe']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Borrar Producto'), array('action' => 'delete', $shoe['Shoe']['id']), null, __('Estás seguro de querer eliminarlo # %s?', $shoe['Shoe']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('Lista Productos'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('Nuevo Producto'), array('action' => 'add')); ?> </li>
	</ul>
</div>
