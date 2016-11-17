<?php
App::uses('AppController', 'Controller');
/**
 * Shoes Controller
 *
 * @property Shoe $Shoe
 * @property PaginatorComponent $Paginator
 */
class ShoesController extends AppController {

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
		$this->Shoe->recursive = 0;
		$this->set('shoes', $this->Paginator->paginate());
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		if (!$this->Shoe->exists($id)) {
			throw new NotFoundException(__('Invalid shoe'));
		}
		$options = array('conditions' => array('Shoe.' . $this->Shoe->primaryKey => $id));
		$this->set('shoe', $this->Shoe->find('first', $options));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Shoe->create();
			if ($this->Shoe->save($this->request->data)) {
				$this->Session->setFlash(__('El producto ha sido añadido'));
				return $this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('El producto no ha sido añadido. Por favor, inténtelo de nuevo.'));
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
		if (!$this->Shoe->exists($id)) {
			throw new NotFoundException(__('Invalid shoe'));
		}
		if ($this->request->is(array('post', 'put'))) {
			if ($this->Shoe->save($this->request->data)) {
				$this->Session->setFlash(__('Los cambios han sido guardados'));
				return $this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Los cambios no han sido guardados. Por favor, inténtelo de nuevo.'));
			}
		} else {
			$options = array('conditions' => array('Shoe.' . $this->Shoe->primaryKey => $id));
			$this->request->data = $this->Shoe->find('first', $options);
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
		$this->Shoe->id = $id;
		if (!$this->Shoe->exists()) {
			throw new NotFoundException(__('Invalid shoe'));
		}
		$this->request->onlyAllow('post', 'delete');
		if ($this->Shoe->delete()) {
			$this->Session->setFlash(__('El producto ha sido eliminado'));
		} else {
			$this->Session->setFlash(__('El producto no ha sido eliminado. Por favor, inténtelo de nuevo.'));
		}
		return $this->redirect(array('action' => 'index'));
	}}
