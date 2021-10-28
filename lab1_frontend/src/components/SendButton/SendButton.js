import {Button, Modal} from "react-bootstrap";
import {useState} from "react";
import ResultTable from "../ResultTable/ResultTable";


const SendButton = ({method, url, data}) => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [responseData, setResponseData] = useState([]);
    const [reqType, setReqType] = useState(null);

    function createBody(){
        console.log(responseData);
    }

    function sendData(event) {
          fetch(url + '/' + data[0].id)
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                    setIsLoaded(true);
                    setResponseData([result]);
                },
                (error) => {
                    console.log(error)
                    setIsLoaded(true);
                    setError(error);
                },
            ).catch(async (error) => {
                await setError(error);
                console.log('ошибка твоя мать цопа сдохни падаль ебанная')
         })
    }

        if (error) {
            return (
                <Modal.Dialog>
                    <Modal.Header closeButton>
                        <Modal.Title>Ошибка</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <p>{error.message}.</p>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="secondary">Сорян, зря быканул</Button>
                    </Modal.Footer>
                </Modal.Dialog>
            )
        }

        return (
            <div>
                <Button variant="primary" id='send-button' onClick={sendData}>
                    SEND
                </Button>
                <ResultTable data={responseData}/>
            </div>
        )
    }

export default SendButton;
