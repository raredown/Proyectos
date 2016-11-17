<?php
App::uses('AppController', 'Controller');
/**
 * Proveedors Controller
 *
 * @property Proveedor $Proveedor
 * @property PaginatorComponent $Paginator
 */
class ProveedorsController extends AppController {

/**
 * Components
 *
 * @var array
 */
	public $components = array('Paginator');

/**
 * index method
 *
 * @return void
 */
	public function index() {
		$this->Proveedor->recursive = 0;
		$this->set('proveedors', $this->Paginator->paginate());
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		if (!$this->Proveedor->exists($id)) {
			throw new NotFoundException(__('Invalid proveedor'));
		}
		$options = array('conditions' => array('Proveedor.' . $this->Proveedor->primaryKey => $id));
		$this->set('proveedor', $this->Proveedor->find('first', $options));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Proveedor->create();
			if ($this->Proveedor->save($this->request->data)) {
				$this->Session->setFlash(__('El proveedor ha sido añadido'));
				return $this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('El proveedor no ha sido añadido. Por favor, inténtelo de nuevo.'));
			}
		}
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		if (!$this->Proveedor->exists($id)) {
			throw new NotFoundException(__('Invalid proveedor'));
		}
		if ($this->request->is(array('post', 'put'))) {
			if ($this->Proveedor->save($this->request->data)) {
				$this->Session->setFlash(__('Los cambios han sido guardados'));
				return $this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Los cambios no han sido guardados. Por favor, inténtelo de nuevo.'));
			}
		} else {
			$options = array('conditions' => array('Proveedor.' . $this->Proveedor->primaryKey => $id));
			$this->request->data = $this->Proveedor->find('first', $options);
		}
	}

/**
 * delete method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function delete($id = null) {
		$this->Proveedor->id = $id;
		if (!$this->Proveedor->exists()) {
			throw new NotFoundException(__('Invalid proveedor'));
		}
		$this->request->onlyAllow('post', 'delete');
		if ($this->Proveedor->delete()) {
			$this->Session->setFlash(__('El proveedor ha sido eliminado'));
		} else {
			$this->Session->setFlash(__('El proveedor no ha sido eliminado. Por favor, inténtelo de nuevo.'));
		}
		return $this->redirect(array('action' => 'index'));
	}}
