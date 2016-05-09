<?php

class FornecedorController extends BaseController {
    public function saveAction(){
    	$fornecedor_arr = json_decode($this->request->get("data"), true);
    	$endereco_arr = $fornecedor_arr["endereco"];	
    	$endereco = new Endereco();
    	$fornecedor = new Fornecedor();

    	if ($endereco->save($endereco_arr)){
    		$fornecedor->id_endereco = $endereco->id;
    		if (!$fornecedor->save($fornecedor_arr)){
    			$errors = [];
                foreach ($fornecedor->getMessages() as $error) {
                    $errors[] = $error->getMessage();
                }
                return ["success" => false, "errors" => $errors];
    		}
    	} else {
    		//return ["success" => false, "errors" => ["nada nao"]];
    		$errors = [];
            foreach ($endereco->getMessages() as $error) {
                $errors[] = $error->getMessage();
            }
            return ["success" => false, "errors" => $errors];
    	}

    	return ["id" => $fornecedor->id];

    }

    public function findAction(){
        try {
            $fornecedores = Fornecedor::find();
            $arr = [];
            foreach ($fornecedores as $fornecedor){
            	$endereco = $fornecedor->endereco;
            	$fornecedor = get_object_vars($fornecedor);
            	if ($endereco){
	            	$fornecedor["endereco"] = $endereco;
	            }
            	$arr[] = $fornecedor;
            }
            return ["data" => $arr];
        } catch (Exception $e){           
            print_r($e);
        }
        exit;
    }
}