<div class="shoes index">
	<h2><?php echo __('Productos'); ?></h2>
	<table cellpadding="0" cellspacing="0">
	<tr>
			<th><?php echo $this->Paginator->sort('id'); ?></th>
			<th><?php echo $this->Paginator->sort('precio'); ?></th>
			<th><?php echo $this->Paginator->sort('nombre'); ?></th>
			<th><?php echo $this->Paginator->sort('creacion'); ?></th>
			<th><?php echo $this->Paginator->sort('modificacion'); ?></th>
			<th class="actions"><?php echo __('Acciones'); ?></th>
	</tr>
	<?php foreach ($shoes as $shoe): ?>
	<tr>
		<td><?php echo h($shoe['Shoe']['id']); ?>&nbsp;</td>
		<td><?php echo h($shoe['Shoe']['precio']); ?>&nbsp;</td>
		<td><?php echo h($shoe['Shoe']['name']); ?>&nbsp;</td>
		<td><?php echo h($shoe['Shoe']['created']); ?>&nbsp;</td>
		<td><?php echo h($shoe['Shoe']['modified']); ?>&nbsp;</td>
		<td class="actions">
			<?php echo $this->Html->link(__('Ver'), array('action' => 'view', $shoe['Shoe']['id'])); ?>
			<?php echo $this->Html->link(__('Editar'), array('action' => 'edit', $shoe['Shoe']['id'])); ?>
			<?php echo $this->Form->postLink(__('Borrar'), array('action' => 'delete', $shoe['Shoe']['id']), null, __('Estás seguro de querer eliminarlo # %s?', $shoe['Shoe']['id'])); ?>
		</td>
	</tr>
<?php endforeach; ?>
	</table>
	<p>
	<?php
	echo $this->Paginator->counter(array(
	'format' => __('Página {:page} de {:pages}, estás en {:current} página {:count} total, empienza en el {:start}, termina en {:end}')
	));
	?>	</p>
	<div class="paging">
	<?php
		echo $this->Paginator->prev('< ' . __('antes'), array(), null, array('class' => 'prev disabled'));
		echo $this->Paginator->numbers(array('separator' => ''));
		echo $this->Paginator->next(__('después') . ' >', array(), null, array('class' => 'next disabled'));
	?>
	</div>
</div>
<div class="actions">
	<h3><?php echo __('Acciones'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Nuevo Producto'), array('action' => 'add')); ?></li>
	</ul>
</div>
