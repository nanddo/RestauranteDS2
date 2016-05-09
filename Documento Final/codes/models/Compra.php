<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;


class Compra extends Model {
	
    public $id;
    public $item;
    public $quantidade;
    public $preco;
    public $data;
	
	public function getSchema()  {
        return "public";
    }
}

?>
