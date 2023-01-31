const options = input.attributes.options || [];
const numberOfOptions = configuration.numberOfOptions;

if (numberOfOptions < options.length) {
	const button = fragmentElement.querySelector('.multi-select-list-button');

	const missionOptions = options.slice(numberOfOptions);

	const template = fragmentElement.querySelector(
		'.multi-select-list-option-template'
	);
	const fieldSet = fragmentElement.querySelector(
		'.multi-select-list-fieldset'
	);

	button.addEventListener('click', () => {
		missionOptions.forEach((option) => {
			const node = template.content.cloneNode(true);

			const input = node.querySelector('input');
			input.value = option.value;

			const text = node.querySelector('.custom-control-label-text');
			text.textContent = option.label;

			fieldSet.appendChild(node);
		});

		fieldSet.removeChild(button);
	});
}
