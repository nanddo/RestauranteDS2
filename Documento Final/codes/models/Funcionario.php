<?php

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;


class Funcionario extends Model {
	
    public $id;
    public $tipo;
    public $nome;
    public $id_endereco;
    public $telefone;
    public $cpf;
    public $nome_de_usuario;

    public function initialize(){
        $this->belongsTo("id_endereco", "Endereco", "id");
    }
	
	public function getSchema()  {
        return "public";
    }
}

?>
