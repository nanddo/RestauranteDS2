<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;


class Endereco extends Model {
	
    public $id;
    public $logradouro;
    public $rua;
    public $numero;
    public $bairro;
    public $cidade;
    public $estado;
    public $cep;

    public function initialize(){
        $this->hasMany("id", "Fornecedor", "id_endereco");
        $this->hasMany("id", "Funcionario", "id_endereco");
    }
	
	public function getSchema()  {
        return "public";
    }
}

?>
