import clsx from "clsx";
import { HTMLAttributes, useId } from "react";

type Props = {
  placeholder: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
  value?: string;
  outerContainerClassName?: HTMLAttributes<HTMLDivElement>["className"];
};

export default function SearchBar({
  placeholder,
  onChange,
  value,
  outerContainerClassName,
}: Props) {
  const id = useId();
  return (
    <>
      <div
        className={clsx(
          outerContainerClassName,
          "relative flex items-center h-8 rounded-lg focus-within:shadow-lg bg-white overflow-hidden"
        )}
      >
        <div className="grid place-items-center h-full w-12 text-gray-300">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
            />
          </svg>
        </div>

        <input
          className="w-full h-full outline-none text-sm text-gray-700 pr-2 bg-white selection:text-white selection:bg-blue-500"
          type="text"
          id={id}
          placeholder={placeholder}
          onChange={onChange}
          value={value}
        />
      </div>
    </>
  );
}
