<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;

//TODO: Remover total de pedidos
class Comanda extends Model {
	
    public $id;
    public $data;
    public $ativa;
    public $nome;
    public $pedidos;
	
	public function getSchema()  {
        return "public";
    }
}

?>
