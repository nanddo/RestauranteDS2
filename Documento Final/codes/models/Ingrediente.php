<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;

class Ingrediente extends Model {
	public $id_item;
	public $id_produto;
	public $quantidade;

	public function initialize(){
        $this->belongsTo("id_produto", "Produto", "id");
        $this->belongsTo("id_item", "Item", "id");
    }

	public function getSchema()  {
        return "public";
    }
}