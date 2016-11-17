<?php
App::uses('Shoe', 'Model');

/**
 * Shoe Test Case
 *
 */
class ShoeTest extends CakeTestCase {

/**
 * Fixtures
 *
 * @var array
 */
	public $fixtures = array(
		'app.shoe'
	);

/**
 * setUp method
 *
 * @return void
 */
	public function setUp() {
		parent::setUp();
		$this->Shoe = ClassRegistry::init('Shoe');
	}

/**
 * tearDown method
 *
 * @return void
 */
	public function tearDown() {
		unset($this->Shoe);

		parent::tearDown();
	}

}
