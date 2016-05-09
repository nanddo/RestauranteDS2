<?php

//http://localhost/restaurante/fornecedor/save/?data={%22nome%22:%22Fernando%20Messias%22,%20%22telefone%22:%20%2279%2098833848%22,%20%22cnpj%22:%20%2200-000-000/0000-00%22,%20%22email%22:%22fernandoxlr@live.com%22,%20%22id_endereco%22:1}

use Phalcon\Mvc\Model;
use Phalcon\Mvc\Model\Message;


class Fornecedor extends Model {
	
    public $id;
    public $nome;
    public $telefone;
    public $cnpj;
    public $email;
    public $id_endereco;
	
    public function initialize(){
        $this->belongsTo("id_endereco", "Endereco", "id");
    }
    

	public function getSchema()  {
        return "public";
    }
}

?>
