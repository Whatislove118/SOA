import {Form} from "react-bootstrap";


const Select = ({field}) => {
    return (
        <div>
            <Form.Label>{field.placeholder}</Form.Label>
            <Form.Select aria-label="Default select example">
                <option>{field.placeholder}</option>

            {
                field.value.map(v => {
                    return <option value={v}>{v}</option>
            })
            }
            </Form.Select>
        </div>
    )

}

export default Select;
