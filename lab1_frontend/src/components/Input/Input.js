import {Form} from "react-bootstrap";


const Input = ({field, onChange}) => {
    return (
        <Form.Group>
            <Form.Label>{field.placeholder}</Form.Label>
            <Form.Control id={field.id} type={field.type} placeholder={field.placeholder} step={field.step} onChange={onChange}/>
        </Form.Group>
    )

}

const writeToVariable = (event) => {
    if (event.id.equals('x')){

    }
}

export default Input;
