<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;


class Produto extends Model {
	
    public $id;
    public $nome;
    public $preco;
	
	public function getSchema()  {
        return "public";
    }
}

?>
