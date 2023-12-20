const ServiceCard = ({ service }) => {
    const { title, description, link, imageSrc } = service;

    return (
        <div className="service-card">
            <h2>{title}</h2>
            <img src={imageSrc} alt={title} className="service-image" />
            <p>{description}</p>
            <a href={link} className="service-link" target="_blank" rel="noopener noreferrer">
                Learn More
            </a>
        </div>
    );
};
