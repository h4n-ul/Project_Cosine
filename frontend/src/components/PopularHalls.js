import Card from "./Card";

const PopularHalls = () => {
  const cards = [
    { id: 1, name: '', contents: '', src: "https://daisyui.com/images/stock/photo-1559703248-dcaaec9fab78.jpg" },
    { id: 2, name: '', contents: '', src: "https://daisyui.com/images/stock/photo-1565098772267-60af42b81ef2.jpg" },
    { id: 3, name: '', contents: '', src: "https://daisyui.com/images/stock/photo-1572635148818-ef6fd45eb394.jpg" },
    { id: 4, name: '', contents: '', src: "https://daisyui.com/images/stock/photo-1494253109108-2e30c049369b.jpg" },
    { id: 5, name: '', contents: '', src: "https://daisyui.com/images/stock/photo-1550258987-190a2d41a8ba.jpg" },
    { id: 6, name: '', contents: '', src: "https://daisyui.com/images/stock/photo-1559181567-c3190ca9959b.jpg" },
    { id: 7, name: '', contents: '', src: "https://daisyui.com/images/stock/photo-1601004890684-d8cbf643f5f2.jpg" },
  ];

  return (
    <div className="carousel carousel-center max-w-md p-4 space-x-4 rounded-box">
      {cards.map((card) => (
        <div className="carousel-item"><Card hallid={card.id} hallname={card.name} contents={card.contents} img={card.src}/></div>
      ))}
    </div>
  );
}

export default PopularHalls;
