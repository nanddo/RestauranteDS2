<?php

use Phalcon\Mvc\Controller;
use Phalcon\Mvc\Dispatcher;

class BaseController extends Controller {
	
	public $debugArray;
	public $lastDebugKey = 1;
	
    /**
     * This action is available for multiple controllers
     */
    public function redis() {
		return $this->getDi()->getRedis();
    }
    
    // After route executed event. Convert everything to JSON response
    public function afterExecuteRoute(Dispatcher $dispatcher) {
        // If the response was sent before (404 example below)
        if ($this->response->isSent()) {
            return;
        }
        
        $data = $dispatcher->getReturnedValue();

        /* Set global params if is not set in controller/action */
        return $this->sendContent(200, "", $data);
    }

    public function sendContent($statusCode, $statusMessage, $content) {
        if ($this->response->isSent()) {
            return;
        }

        if ($content == null) {
            $content = array();
        } else if (!is_array($content)) {
            $content = array($content);
        }

        $content['success'] = isset($content['success']) ? $content['success'] : true;

        if ($this->debugArray) {
            $content["debug"] = $this->debugArray;
        }
    

        $this->response->setContentType('application/json', 'UTF-8');
        $this->response->setHeader('Access-Control-Allow-Headers', 'X-Requested-With'); 
        $this->response->setStatusCode($statusCode, $statusMessage);
        $this->response->setContent(json_encode($content));
        return $this->response->send();
    }


    public function notFoundAction() {
        $this->response->setStatusCode(404, 'Not Found');
        $this->response->setContentType('application/json', 'UTF-8');
        $this->response->setContent(json_encode(array("success" => false, "message" => "This endpoint doesn't exist. ".implode(",",$this->request->getQuery()) )));
        return $this->response->send();
    }
	
	public function buildErrorReturn($message) {
		return array("success" => false, "message" => $message);
	}
	
	public function buildReturn($data, $key = 'data') {
		return array("success" => true, $key => $data);
	}
	
	public function debug($obj, $key = 0) {
		if (!$this->debugArray) {
			$this->debugArray = array();
		}
		if (!$key) {
			$key = $this->lastDebugKey++;
		}
		$this->debugArray[$key] = $obj;
	}

    public function saveAction(){
        try {
            $model = ucfirst($this->router->getControllerName());

            $instance = new $model();
            $data = json_decode($this->request->get("data"), true);

            if (!$instance->save($data)){
                $errors = [];
                foreach ($instance->getMessages() as $error) {
                    $errors[] = $error->getMessage();
                }
                return ["success" => false, "errors" => $errors];
            } else {
                return ["id" => $instance->id];
            }
        } catch (Exception $e){
            print_r($e);
        }
    }
    
    public function findAction(){
        try {
            $model = ucfirst($this->router->getControllerName());

            $where = $this->request->get("data");

            $data = $model::find($where ? $where : null);

            return ["data" => $data];
        } catch (Exception $e){
            print_r($e);
        }
    }

    public function deleteAction(){
        try {
            $model = ucfirst($this->router->getControllerName());
            $data = json_decode($this->request->get("data"), true);
            
            $item = $model::findFirst($data["id"]);
            $item->delete();
        } catch (Exception $e){
            print_r($e);
        }
    }
}

?>