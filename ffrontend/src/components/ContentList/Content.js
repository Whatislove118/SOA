import React, {useEffect, useState} from 'react';
import {CellInput} from "./CellInput/CellInput";
import {CellSelectClimate} from "./CellSelectCapital/CellSelectClimate";
import {CellSelectorGovernment} from "./CellSelectorGovernment/CellSelectorGovernment";

export const Content = ({content, onClick, checkEnter, sendUpdate}) => {

    return (

        content.map(item =>
            <tr key={item.id}>
                {/*<td>{item.id}</td>*/}
                <td>{item.id}</td>
                {/*<td>{item.name}</td>*/}
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "name", content)} props={item.name} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "coordinate_x", content)} props={item.coordinates.x} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "coordinate_y", content)} props={item.coordinates.y} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "creationDate", content)} props={item.creationDate} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "area", content)} props={item.area} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "population", content)} props={item.population} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "metersAboveSeaLevel", content)} props={item.metersAboveSeaLevel} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "establishmentDate", content)} props={item.establishmentDate} /></td>
                {/*<td>{String(item.capital)}</td>*/}
                <td><CellSelectClimate
                    props={item.climate}
                    sendUpdate={(content) => sendUpdate(item.id, "climate", content)}
                /></td>
                {/*<td>{item.government}</td>*/}
                <td><CellSelectorGovernment
                    props={item.government}
                    sendUpdate={(content) => sendUpdate(item.id, "government", content)}
                /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "governor_height", content)} props={item.governor.height} /></td>
                <td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "governor_birthday", content)} props={item.governor.birthday} /></td>
                {/*<td><CellInput checkEnter={(e, content) => checkEnter(e, item.id, "governor_birthday", content)} props={item.governor.birthday} /></td>*/}
                {/*<td>{item.governor.birthday.birthday}</td>*/}
                <button onClick={(event) => onClick(event.target.value)} value={item.id}>??????????????</button>
            </tr>)
    );
};
