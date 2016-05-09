<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;

class Ingrediente extends Model {
	$id_item;
	$id_produto;
	$quantidade;

	public function getSchema()  {
        return "public";
    }
}