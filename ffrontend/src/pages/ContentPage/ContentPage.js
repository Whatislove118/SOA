import React, {useEffect, useState} from 'react';
import {Content} from "../../components/ContentList/Content";
import {toast, Toaster} from "react-hot-toast";
import {PaginationList} from "../../paginationList/paginationList";
import {countPage} from "../../Utils/countPage";
import style from "../../paginationList/paginationList.scss"

export const ContentPage = () => {
    const [content, setContent] = useState([]);
    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [coordinate_x, setCoordinateX] = useState("");
    const [coordinate_y, setCoordinateY] = useState("");
    const [creationData, setData] = useState("");
    const [area, setArea] = useState("");
    const [population, setPopulation] = useState("");
    const [metersAboveSeaLevel, setMASL] = useState("");
    const [establishmentDate, setEstablishmentDate] = useState("");
    const [government, setGovernment] = useState("");
    const [climate, setClimate] = useState("");
    const [governor_height, setGovernorHeight] = useState("");
    const [governor_birthday, setGovernorBirthday] = useState("");
    const [sortStructure, setSortStructure] = useState({
        id: false,
        name: false,
        coordinates_x: false,
        coordinates_y: false,
        area: false,
        population: false,
        metersAboveSeaLevel: false,
        establishmentDate: false,
        climate: false,
        government: false,
        governor_height: false,
        governor_birthday: false,
    });

    const [currentPage, setCurrentPage] = useState(1);
    const [perPage, setPerPage] = useState(5);
    const [totalCount, setTotalCount] = useState(10);
    const pagesCount = Math.ceil(totalCount/perPage)
    const pages = []
    countPage(pages, pagesCount, currentPage);

    // const getPage = () => {
    //     fetch(`http://localhost:8080/Lab1/all/city?page=${currentPage}&pageSize=${perPage}`)
    //         .then(res => res.json())
    //         .then(
    //             (result) => {
    //                 setContent(result);
    //             }
    //         )
    // }
                               
    // useEffect(() => {
    //             countPage(pages, pagesCount, currentPage);
    // }, [content])

    const getAll = () => {
        fetch(`http://localhost:8080/city`)
            .then(res => res.json())
            .then(status)
            .then(
                (result) => {
                    setContent(result)
                    setTotalCount(result.length)
                    countPage(pages, pagesCount, currentPage);
                    // getPage();
                }
            ).catch(error => {
            console.log('error, sry')
        })
    }

    useEffect(() => {
        getAll();
    }, [])

    useEffect(() => {
        console.log(sortStructure);
        let url = `http://localhost:8080/city?`
        for (let field in sortStructure) {
            if (sortStructure[field] === true) {
                url += `sort=${field}&`;
            }
        }
        fetch(url)
            .then(res => res.json())
            .then(status)
            .then(
                (result) => {
                    setContent(result);
                    countPage(pages, pagesCount, currentPage);
                    // getPage();
                }
            ).catch(error => {
            console.log('error, sry')
        })
    }, [sortStructure])

    useEffect(() => {
        sendFilter();
        countPage(pages, pagesCount, currentPage);
    }, [id, name, coordinate_x, coordinate_y, creationData, area, population, metersAboveSeaLevel, establishmentDate, climate,
        government, governor_height, governor_birthday])


    const createURL = () => {
        let str ="?"
        if (id !== ""){
            str += `id=${id}`
        }
        if (name !== "") {
            str += `name=${name}&`
        } if (coordinate_x !== "") {
            str += `coordinates_x=${coordinate_x}&`;
        } if (coordinate_y !== "") {
            str += `coordinates_y=${coordinate_y}&`;
        } if (creationData !== "") {
            str += `creationDate=${creationData}&`;
        } if (area !== "") {
            str += `area=${area}&`;
        } if (population !== "") {
            str += `population=${population}&`;
        } if (metersAboveSeaLevel !== "") {
            str += `metersAboveSeaLevel=${metersAboveSeaLevel}&`;
        } if (establishmentDate !== "") {
            str += `establishmentDate=${establishmentDate}&`;
        } if (climate !== "") {
            str += `climate=${climate}&`;
        } if (government !== "") {
            str += `government=${government}&`;
        } if (governor_birthday !== "") {
            str += `governor_birthday=${governor_birthday}&`;
        }if (governor_height !== "") {
            str += `governor_height=${governor_height}&`;
        }
        return str;
    }

    const sendFilter = () => {
        fetch(`http://localhost:8080/city${createURL()}`)
            .then(res => res.json())
            .then(status)
            .then(
                (result) => {
                    setContent(result)
                }
            ).catch(error => {
                console.log('error, sry')
        })
    }

    function status(res) {
        if (!res.ok) {
            return Promise.reject()
        }
        return res;
    }

    //todo return
    const city = () => {
        let c = {
            name : name,
            coordinates : {
                x : parseInt(coordinate_x),
                y : parseFloat(coordinate_y)
            },
            area : parseInt(area),
            population : parseInt(population),
            metersAboveSeaLevel : parseInt(metersAboveSeaLevel),
            establishmentDate : establishmentDate,
            climate : climate,
            government : government,
            governor : {
                height : parseFloat(governor_height),
                birthday: governor_birthday
            }
        };

        return c;
    }
    const clearForm = () => {
        setId("");
        setName("");
        setCoordinateX("");
        setCoordinateY("");
        setData("");
        setArea("");
        setPopulation("");
        setMASL("");
        setEstablishmentDate("");
        setClimate("");
        setGovernment("");
        setGovernorBirthday("");
        setGovernorHeight("");
    }

    const create = () => {
        fetch("http://localhost:8080/city/", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(city())
        }).then(sendFilter);
    }

    const sendSort = (url) => {
        fetch(url)
            .then(res => res.json())
            .then(
                (result) => {
                    setContent(result);
                }
            )
    }

    const sort = (name, status) => {
        setSortStructure({...sortStructure, [name]: status});

    }



    const deleteCity = (id) => {
        if (id !== "") {
            fetch("http://localhost:8080/city",{
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    "id": parseInt(id)
                })
            }).then(
                (result) => {
                }
            ).then(sendFilter)
        }
    }

    const createJSON = (id, field, content) => {

        switch (field) {
            case("name"):
                return {
                    id: id,
                    name: content
                }
            case ("coordinate_x"):
                return {
                    id: id,
                    coordinate_x: parseInt(content)
                }
            case ("coordinate_y"):
                return {
                    id: id,
                    coordinate_y: parseFloat(content)
                }
            case ("area"):
                return {
                    id: id,
                    area: parseInt(content)
                }
            case ("population"):
                return {
                    id: id,
                    population: parseInt(content)
                }
            case ("metersAboveSeaLevel"):
                return {
                    id: id,
                    metersAboveSeaLevel: parseInt(content)
                }
            case ("establishmentDate"):
                return {
                    id: id,
                    establishmentDate: content
                }
            case ("climate"):
                return {
                    id: id,
                    climate: content
                }
            case ("government"):
                return {
                    id: id,
                    government: content
                }
            case ("governor_height"):
                return {
                    id: id,
                    governor_height: content
                }
            case ("governor_birthday"):
                return {
                    id: id,
                    governor_birthday: content
                }

        }
    }

    const sendUpdate = (id, field, content) => {
        fetch("http://localhost:8080/city/", {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(createJSON(id, field, content))
        })
        .then(async (response) => {
            if (
                Math.trunc(response.status / 100) === 4
                || Math.trunc(response.status / 100) === 5
                || response.status === 429
            ) {
                toast.error(await response.text());
            } else {
                toast.success("Изменено");
            }
        })
    }

    const checkEnter = (e, id, field, content) => {
        if (e.key === 'Enter' || e.keyCode === 13) {
            sendUpdate(id, field, content)
        }
    }

    const deleteByClimate = () => {

        if (climate != "") {
            fetch(`http://localhost:8080/Lab1//delete/capital?climate=${climate}`, {
                method: "DELETE"
            })
                .then(async (response) => {
                    if (
                        Math.trunc(response.status / 100) === 4
                        || Math.trunc(response.status / 100) === 5
                        || response.status === 429
                    ) {
                        toast.error(await response.text());
                    } else {
                        toast.success("Удаленно");
                    }
                })
        } else {
            toast.error("Поле capital незаданно");
        }
    }

    const getWithHigherGovernment = () => {
        if (government != "") {
        fetch(`localhost:8080/city/by/government/higher?government=${government}`)
            .then(res => res.json())
            .then(
                (result) => {
                    setContent(result)
                }
            )
        }
            // .then(async (response) => {
            //     if (
            //         Math.trunc(response.status / 100) === 4
            //         || Math.trunc(response.status / 100) === 5
            //         || response.status === 429
            //     ) {
            //         toast.error(await response.text());
            //     } else {
            //         setContent(await response.json());
            //     }
            // })  // .then(async (response) => {
            //     if (
            //         Math.trunc(response.status / 100) === 4
            //         || Math.trunc(response.status / 100) === 5
            //         || response.status === 429
            //     ) {
            //         toast.error(await response.text());
            //     } else {
            //         setContent(await response.json());
            //     }
            // })
    }

    const getWithLowerGovernment = () => {
        if (government != "") {
            fetch(`localhost:8080/city/by/government/lower?government=${government}`)
                .then(res => res.json())
                .then(
                    (result) => {
                        setContent(result)
                    }
                )
        }
    }

    return (
        <div>
            <button onClick={() => create()}>Создать</button>
            <button onClick={() => clearForm()}>Очистить</button>
            <button onClick={() => deleteCity()}>Удалить</button>
            <button onClick={() => deleteByClimate()}>delete by Climate</button>
            <button onClick={() => getWithHigherGovernment()}>Higher government</button>
            <button onClick={() => getWithLowerGovernment()}>Lower government</button>
            <Toaster />
        <table>
            <tr>
                <td>id<input name="id" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>name<input name="name" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>coordinates_x<input name="coordinates_x" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>coordinates_y<input name="coordinates_y" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>creationDate<input name="creationDate" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>area<input name="area" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>population<input name="population" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>metersAboveSeaLevel<input name="metersAboveSeaLevel" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>establishmentDate<input name="establishmentDate" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>climate<input name="climate" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>government<input name="government" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>governor_height<input name="governor_height" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>governor_birthday<input name="governor_birthday" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td> </td>
            </tr>

            <tr>
                <td><input type="text" value={id} onChange={(event) => setId(event.target.value)}/></td>
                <td><input type="text" value={name} onChange={(event) => setName(event.target.value)}/></td>
                <td><input type="text" value={coordinate_x} onChange={(event) => setCoordinateX(event.target.value)}/></td>
                <td><input type="text" value={coordinate_y} onChange={(event) => setCoordinateY(event.target.value)}/></td>
                <td><input type="date" value={creationData} onChange={(event) => setData(event.target.value)}/></td>
                <td><input type="text" value={area} onChange={(event) => setArea(event.target.value)}/></td>
                <td><input type="text" value={population} onChange={(event) => setPopulation(event.target.value)}/></td>
                <td><input type="text" value={metersAboveSeaLevel} onChange={(event) => setMASL(event.target.value)}/></td>
                <td><input type="text" value={establishmentDate} onChange={(event) => setEstablishmentDate(event.target.value)}/></td>
                <td><select value={climate} onChange={(event) => setClimate(event.target.value)}>
                    <option value=""> </option>
                    <option value="MONSOON">MONSOON</option>
                    <option value="HUMIDSUBTROPICAL">HUMIDSUBTROPICAL</option>
                    <option value="SUBARCTIC">SUBARCTIC</option>
                </select></td>
                <td><select value={government} onChange={(event) => setGovernment(event.target.value)}>
                    <option value=""> </option>
                    <option value="ANARCHY">ANARCHY</option>
                    <option value="DESPOTISM">DESPOTISM</option>
                    <option value="CORPORATOCRACY">CORPORATOCRACY</option>
                    <option value="MERITOCRACY">MERITOCRACY</option>
                    <option value="TOTALITARIANISM">TOTALITARIANISM</option>
                </select></td>
                <td><input type="text" value={governor_height} onChange={(event) => setGovernorHeight(event.target.value)}/></td>
                <td><input type="text" value={governor_birthday} onChange={(event) => setGovernorBirthday(event.target.value)}/></td>
                <td> </td>
            </tr>

            <Content
                current={currentPage}
                perPage={perPage}
                sendUpdate={(id, name, content) => sendUpdate(id, name, content)}
                checkEnter={(e, id, name, content) => checkEnter(e, id, name, content)}
                onClick={(id) => deleteCity(id)}
                content={content}
            />
        </table>

            <div className="pages">
                {pages.map((page, index) => <span
                    key={index}
                    className={currentPage == page ? "current-page" : "page"}
                    onClick={() => {setCurrentPage(page)}}>{page}</span>)}
            </div>
        </div>
);
};




// export const ContentPage = () => {
//     const [objectStructure, setObjectStructure] = useState({});
//     const [content, setContent] = useState({});
//
//     const location = useLocation();
//     const dispatch = useDispatch();
//
//     //todo ?????
//     useEffect(() => {
//         setObjectStructure(
//             getObjectStructureByPathName(
//                 `/${location.pathname.split('/')[1]}`
//             )
//         )
//         let arr = [];
//         for (let i = 0; i < 10; i++){
//             switch (`/${location.pathname.split('/')[1]}`) {
//                 default:
//                     arr.push({});
//                 case city_page:
//                     arr.push(constructCity({}));
//                     break;
//                 case coordinate_page:
//                     arr.push(constructCoordinate({}));
//                     break;
//                 case human_page:
//                     arr.push(constructHuman({}));
//                     break;
//             }
//         }
//         setContent(arr);
//     }, [location.pathname])
//
//     //todo ???
//     useEffect(() => {
//         dispatch(setObject({}));
//     }, [location.pathname])
//
//     const getObjectStructureByPathName = (path) => {
//         switch (path) {
//             default:
//                 return {};
//             case city_page:
//                 return CityStructure;
//             case coordinate_page:
//                 return CoordinatesStructure;
//             case human_page:
//                 return HumanStructure;
//         }
//     }
//
//     const mem = [
//         {
//             "id": 249,
//             "name": "MSK",
//             "coordinates": {
//                 "id": 250,
//                 "x": 21,
//                 "y": 32.2
//             },
//             "creationDate": {
//                 "date": {
//                     "year": 2021,
//                     "month": 10,
//                     "day": 3
//                 },
//                 "time": {
//                     "hour": 12,
//                     "minute": 11,
//                     "second": 55,
//                     "nano": 895365000
//                 }
//             },
//             "area": 4,
//             "population": 9,
//             "metersAboveSeaLevel": 3,
//             "timezone": 3.3,
//             "capital": true,
//             "government": "DEMARCHY",
//             "governor": {
//                 "id": 251,
//                 "name": "Vasya"
//             }
//         },
//         {
//             "id": 261,
//             "name": "B",
//             "coordinates": {
//                 "id": 262,
//                 "x": 20,
//                 "y": 31.1
//             },
//             "creationDate": {
//                 "date": {
//                     "year": 2021,
//                     "month": 10,
//                     "day": 3
//                 },
//                 "time": {
//                     "hour": 21,
//                     "minute": 47,
//                     "second": 44,
//                     "nano": 522836000
//                 }
//             },
//             "area": 10,
//             "population": 8,
//             "metersAboveSeaLevel": 2,
//             "timezone": 2.2,
//             "capital": false,
//             "government": "DEMARCHY",
//             "governor": {
//                 "id": 263,
//                 "name": "vv"
//             }
//         }
//     ]
//
// // usememo хук
//     return (
//         <div>
//             {/*<Filters*/}
//             {/*    object={location*/}
//             {/*        .pathname.split('/')[1]*/}
//             {/*        .toUpperCase()}*/}
//             {/*    method={'Post'}*/}
//             {/*    objectStructure={objectStructure}*/}
//             {/*    onSubmitAction={() => {}}*/}
//             {/*/>*/}
//
//
//             <Content cont={mem} />
//             {/*<PaginationList itmes={content} />*/}
//         </div>
//     );
// };
