

<?php

class ProdutoController extends BaseController {
    public function testeAction(){
        $message = json_decode($_GET['message']);
        header("Content-type: text/html; charset=utf-8");
        echo "Message recebida as ".$message->date." de ".$message->name.":\n".$message->message;
        exit;
    }

    public function saveAction(){
        try {
            $produto = new Produto();
            $data = json_decode($this->request->get("data"), true);

            $produto->nome = $data["nome"];
            $produto->preco = $data["preco"];
            $ingredientesArr = $data["ingredientes"];

            if ($produto->save($data)){
            	foreach ($ingredientesArr as $ingredienteArr) {
            		$item = $ingredienteArr["item"];
            		$ingrediente = new Ingrediente();
            		$ingrediente->id_item = $item["id"];
            		$ingrediente->id_produto = $produto->id;
            		$ingrediente->quantidade = $ingredienteArr["quantidade"];
            		if (!$ingrediente->save()){
		            	$errors = [];
		                foreach ($ingrediente->getMessages() as $error) {
		                    $errors[] = $error->getMessage();
		                }
		                return ["success" => false, "errors" => $errors];		
            		}
            	}
                return ["id" => $instance->id];
            } else {
                $errors = [];
                foreach ($produto->getMessages() as $error) {
                    $errors[] = $error->getMessage();
                }
                return ["success" => false, "errors" => $errors];
            }
        } catch (Exception $e){
            print_r($e);
        }
    }
}

/*
{"ingredientes":[{"item":{"limiteMinimo":2,"nome":"Item 1","quantidade":5},"quantidade":2},{"item":{"limiteMinimo":3,"nome":"Item 2","quantidade":10},"quantidade":4},{"item":{"limiteMinimo":2,"nome":"Item 1","quantidade":5},"quantidade":2},{"item":{"limiteMinimo":3,"nome":"Item 2","quantidade":10},"quantidade":4}],"nome":"Pizza","preco":50.0}
*/