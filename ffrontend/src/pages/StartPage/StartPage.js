import React from 'react';

export const StartPage = () => {

    return (
        <div>
            <div>
                <h1>Лабораторная работа #1</h1>
                <h3>Почикалин В.О.</h3>
                <h4>Вариант 2007</h4>
            </div>
            <div>
                {routes.map(route =>
                    <div>

                        <div>

                        </div>
                    </div>
                )}
            </div>
            <img src={"http://pics.livejournal.com/tumbler73/pic/0000y0xy"} width={'1000px'} height={'1000px'}/>
        </div>
    );
};
