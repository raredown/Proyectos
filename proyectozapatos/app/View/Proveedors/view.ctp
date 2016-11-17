<div class="proveedors view">
<h2><?php echo __('Proveedor'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($proveedor['Proveedor']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Nombre'); ?></dt>
		<dd>
			<?php echo h($proveedor['Proveedor']['name']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Creación'); ?></dt>
		<dd>
			<?php echo h($proveedor['Proveedor']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modificación'); ?></dt>
		<dd>
			<?php echo h($proveedor['Proveedor']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Acciones'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Editar Proveedor'), array('action' => 'edit', $proveedor['Proveedor']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Borrar Proveedor'), array('action' => 'delete', $proveedor['Proveedor']['id']), null, __('Estas seguro de eliminarlo # %s?', $proveedor['Proveedor']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('Lista Proveedores'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('Nuevo Proveedor'), array('action' => 'add')); ?> </li>
	</ul>
</div>
