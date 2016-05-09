<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;


class Item extends Model {
	
    public $id;
    public $nome;
    public $quantidade;
    public $limite;
	
	public function getSchema()  {
        return "public";
    }
}

?>
