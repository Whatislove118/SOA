import {map} from "react-bootstrap/ElementChildren";
import {Table} from "react-bootstrap";


const ResultTable = ({data}) => {
    console.log(data);
    return (
        <div id={'result-table'}>
        <Table striped bordered hover size="sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>name</th>
                <th>X</th>
                <th>Y</th>
                <th>area</th>
                <th>population</th>
                <th>metersAboveSeaLevel</th>
                <th>establishmentDate</th>
                <th>climate</th>
                <th>government</th>
                <th>height</th>
                <th>birthday</th>
            </tr>
            </thead>
            <tbody>
             {data.map(c => {
                 return (
                     <tr>
                         <td>{c.id}</td>
                         <td>{c.name}</td>
                         <td>{c.coordinates.x}</td>
                         <td>{c.coordinates.y}</td>
                         <td>{c.area}</td>
                         <td>{c.population}</td>
                         <td>{c.metersAboveSeaLevel}</td>
                         <td>{c.establishmentDate}</td>
                         <td>{c.climate}</td>
                         <td>{c.government}</td>
                         <td>{c.governor.height}</td>
                         <td>{c.governor.birthday.birthday}</td>
                    </tr>
                 )
             })}
            </tbody>
        </Table>

        </div>
    )
}



export default ResultTable;
