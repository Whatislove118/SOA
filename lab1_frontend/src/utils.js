
import axios from "axios";

const server_url = 'http://localhost:8080/city/';


async function sendGet(id){
    let url = server_url+id
    console.log(url)
    let response = await fetch(url);
    if (response.ok){
        let json = await response.json()
        console.log(json)
        return await json;
    }else{
        alert('ОШИБКА')
    }
}
export default sendGet;
